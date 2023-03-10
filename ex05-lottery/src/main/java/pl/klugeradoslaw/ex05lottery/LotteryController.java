package pl.klugeradoslaw.ex05lottery;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/lottery")
public class LotteryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Integer> userNumbers = getNumbers(req);
        List<Integer> lotteryNumbers = Lottery.getRandomNumbers();
        List<Integer> winnerNumbers = Lottery.getCommonElements(userNumbers, lotteryNumbers);
        LotteryResultDto lotteryResult = new LotteryResultDto(userNumbers, lotteryNumbers, winnerNumbers);
        req.setAttribute("lotteryResult", lotteryResult);
        req.getRequestDispatcher("result.jsp").forward(req, resp);

    }

    private List<Integer> getNumbers(HttpServletRequest request) {
        String[] numbersParam = request.getParameterValues("numbers");
        return Arrays.stream(numbersParam)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

    }
}
