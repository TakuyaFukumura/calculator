package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.OldCalculatedData;
import model.Calculation;
import model.Common;
import model.Delete;
import model.Input;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("displayデータ", "0");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response); ////初期ページへフォワード移動
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //文字コード系
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		OldCalculatedData oldCalculatedData = new OldCalculatedData();
		if ((OldCalculatedData) session.getAttribute("最後の演算式") != null)
			oldCalculatedData = (OldCalculatedData) session.getAttribute("最後の演算式");
		String display = request.getParameter("display");
		String clickData = request.getParameter("clickData");
		if ("ＡＣ".equals(clickData))
			oldCalculatedData.setErrorFlag(true); //AC押されたらtrueに戻す


		if (!oldCalculatedData.isErrorFlag()) { //エラーセット時の処理
		} else if (Input.checkInput(clickData)) {//入力処理
			if (Common.checkNumber(display) || Calculation.judgmentSymbolG(clickData))
				display = Input.inputProcessing(display, clickData);
		} else if (Delete.checkDelete(clickData)) {//削除処理
			display = Delete.deleteProcessing(display, clickData);
		} else if (Calculation.symbolIsEqual(clickData)) { //計算処理
			if (display.equals(oldCalculatedData.getOldResult()))
				display += oldCalculatedData.getOldOperator(); //イコール二度押し用で演算子を後ろにくっつける
			oldCalculatedData.setOldOperator(Calculation.getLastArithmetic(display));//演算子記録
			display = Calculation.calculationProcessing(display);
			if (Common.checkNumberOfDigits(display) > 12)
				oldCalculatedData.setErrorFlag(false);//オーバーフロー時の処理を書く
			oldCalculatedData.setOldResult(display);//演算結果記録
			session.setAttribute("最後の演算式", oldCalculatedData);
		}

		request.setAttribute("displayデータ", display);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response); //フォワード
	}
}
