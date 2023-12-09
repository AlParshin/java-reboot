package ru.sberbank.edu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/finance")
public class FinancialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/finance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String sum = req.getParameter("sum");
        String percentage = req.getParameter("percentage");
        String years = req.getParameter("years");

        int res = 0;
        int percent = 0;
        int year = 0;

        PrintWriter out = resp.getWriter();

        try {
            res = Integer.parseInt(sum);
            percent = Integer.parseInt(percentage);
            year = Integer.parseInt(years);

            if (res < 50000) {
                out.write("<html>");
                out.write("<body>");
                out.write("<h1>Ошибка</h1>");
                out.write("<br>");
                out.write("Минимальная сумма на момент открытия = 50 000 рублей!");
                out.write("</body>");
                out.write("</html>");
            }
            else if ((res <= 0) || (percent <= 0) || (year <= 0)) {
                out.write("<html>");
                out.write("<body>");
                out.write("<h1>Результат</h1>");
                out.write("<br>");
                out.write("Неверные данные! Значения должны быть больше нуля!");
                out.write("</body>");
                out.write("</html>");
            }
            else {
                out.write("<html>");
                out.write("<body>");
                for (int i = 1; i <= year; i++) {
                    res += res*percent/100;
                }
                out.write("<h1>Результат</h1>");
                out.write("Итоговая сумма = " + res);
                out.write("</body>");
                out.write("</html>");
            }

        }
        catch (NumberFormatException ex) {
            out.write("<html>");
            out.write("<body>");
            out.write("<h1>Результат</h1>");
            out.write("<br>");
            out.write("Значения должны быть только цифровые! ");
            out.write("Иные символы не допускаются!");
            out.write("</body>");
            out.write("</html>");
        }

    }
}