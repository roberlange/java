public class DownloadFileHandler {

    /**
     * Método para download do arquivo
     *
     * @param file
     */
    public static void downloadFile(File file, String mimeType) {
        FileInputStream in = null;
        
        try {
        	FacesContext context = FacesContext.getCurrentInstance();
        	HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setHeader("Content-Disposition", "attachment;filename=\""+file.getName()); 
            response.setContentLength((int) file.length()); 
            response.setContentType(mimeType);
            in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] buf = new byte[(int) file.length()];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            context.responseComplete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DownloadFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(DownloadFileHandler.class.getName()).log(Level.SEVERE, null, io);
        } catch (Exception exc) {
            Logger.getLogger(DownloadFileHandler.class.getName()).log(Level.SEVERE, null, exc);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DownloadFileHandler.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }
}
