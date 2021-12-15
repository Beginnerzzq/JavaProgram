package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class UploadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //判断上传的文件是普通的表单还是带文件的表单
            if (!ServletFileUpload.isMultipartContent(request)) {
                return;//如果是普通文件，我们可以直接返回
            } //如果通过了这个if，说明我们的表单是带文件上传的；

            //创建上传文件的保存路径，建议在WEB-INF路径下，安全，用户无法直接访问上传的文件；
            String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
            File uploadFile = new File(uploadPath);
            if (!uploadFile.exists()) {//如果目录不存在，创建这样一个目录；
                uploadFile.mkdir();
            }

            //临时路径，假如文件超过了预期的大小，我们就把他放到一个临时文件中，过几天自动删除，或者提醒用户转存为永久
            String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
            File file = new File(tmpPath);
            if (!file.exists()) {//如果目录不存在，创建这样一个目录；
                file.mkdir();
            }

            //处理上传的文件，一般都需要通过流来获取，我们可以使用request.getInputStream(),原生态的文件上传流获取，十分麻烦
            //但是我们都建议使用 Apache的文件上传组件来实现，common-fileupload，它需要依赖于 commons-io组件；

            //1.创建DiskFileItemFactory对象，处理文件上传路径或者大小限制的；
            DiskFileItemFactory factory = getDiskFileItemFactory(file);
            //2.获取ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);
            //3.处理上传的文件
            String msg = uploadParseRequest(upload, request, uploadPath);

            //servlet请求转发消息
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("info.jsp").forward(request,response);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }


    public static DiskFileItemFactory getDiskFileItemFactory(File file) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置一个缓冲区，当上传的文件大于这个缓冲区的时候，将他放到临时文件中；
        factory.setSizeThreshold(1024 * 1024); //缓存区大小为1M
        factory.setRepository(file);//临时目录的保存目录，需要一个File
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度；
        upload.setProgressListener(new ProgressListener() {
            @Override
            //pBytesRead:已经读取到的文件大小
            //pContentLength ： 文件大小
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小：" + pContentLength + "已上传：" + pBytesRead);
            }
        });

        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总共能够上传文件的大小
        //1024 = 1kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);

        return upload;
    }


    public static String uploadParseRequest(ServletFileUpload upload,HttpServletRequest request,String uploadPath)
            throws FileUploadException, IOException {

        String msg = "";

        //3.把前端请求解析，封装成一个FileItem对象
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()){ //判断上传的文件是普通的表单还是带文件的表单
                //getFieldName指的是前端表单控件的name；
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8"); //处理乱码
                System.out.println(name+":"+value);
            }else { //判断它是上传的文件

                //=======================处理文件===============================//

                //拿到文件名字
                String uploadFileName = fileItem.getName();
                System.out.println("上传的文件名："+uploadFileName);

                if (uploadFileName.trim().equals("")||uploadFileName==null){
                    continue;
                }

                //获得上传的文件名  /images/girl/paojie.png
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //获得文件的后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    /*
                        如果文件后缀名 fileExtName 不是我们所需要的
                        就直接return，不处理，告诉用户文件类型不对。
                    */

                System.out.println("文件信息 [件名："+fileName+"---文件类型"+fileExtName+"]");

                //可以使用UUID（唯一识别的通用码），保证文件名唯一；
                //UUID.randomUUID()，随机生一个唯一识别的通用码；
                String uuidPath = UUID.randomUUID().toString();

                //=======================处理文件完毕===============================//

                //存到哪？ uploadPath
                //文件真实存在的路径 realPath
                String realPath =   uploadPath+"/"+uuidPath;
                //给每个文件创建一个对应的文件夹
                File realPathFile = new File(realPath);
                if (!realPathFile.exists()){
                    realPathFile.mkdir();
                }

                //=======================存放地址完毕===============================//

                //获得文件上传的流
                InputStream inputStream = fileItem.getInputStream();

                //创建一个文件输出流
                //realPath = 真实的文件夹；
                //差了一个文件; 加上输出文件的名字+"/"+uuidFileName
                FileOutputStream fos = new FileOutputStream(realPath+"/"+fileName);

                //创建一个缓冲区
                byte[] buffer = new byte[1024*1024];

                //判断是否读取完毕
                int len = 0;
                //如果大于0说明还存在数据；
                while ((len=inputStream.read(buffer))>0){
                    fos.write(buffer,0,len);
                }

                //关闭流
                fos.close();
                inputStream.close();

                msg = "文件上传成功！";
                fileItem.delete(); //上传成功，清除临时文件
                //=======================文件传输完毕===============================//
            }

        }

        return msg;

    }


}