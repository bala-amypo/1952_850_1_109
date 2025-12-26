public class SimpleStatusServlet extends HttpServlet {

    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        writer.write("Credit Card Reward Maximizer is running");
        writer.flush();
    }
}
