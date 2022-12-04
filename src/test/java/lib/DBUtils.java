package lib;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String url = System.getProperty("db.url");
    private static String userDB = System.getProperty("app.userDB");
    private static String password = System.getProperty("app.password");

    public static void clearAllData() throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userDB, password);
        runner.update(conn, "DELETE FROM credit_request_entity;");
        runner.update(conn, "DELETE FROM payment_entity;");
        runner.update(conn, "DELETE FROM order_entity;");
    }

    public static Status checkPaymentStatus() throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userDB, password);
        val paymentDataSQL = "SELECT status FROM payment_entity;";
        val payment = runner.query(conn, paymentDataSQL, new BeanHandler<>(PaymentModel.class));
        return payment.status;
    }

    public static PaymentModel checkPaymentEmptyStatus() throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userDB, password);
        val paymentDataSQL = "SELECT status FROM payment_entity WHERE status IS NULL;";
        val credit = runner.query(conn, paymentDataSQL, new BeanHandler<>(PaymentModel.class));
        return credit;
    }

    public static Status checkCreditStatus() throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userDB, password);
        val creditDataSQL = "SELECT status FROM credit_request_entity;";
        val credit = runner.query(conn, creditDataSQL, new BeanHandler<>(CreditModel.class));
        return credit.status;
    }

    public static CreditModel checkCreditEmptyStatus() throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userDB, password);
        val creditDataSQL = "SELECT status FROM credit_request_entity WHERE status IS NULL;";
        val credit = runner.query(conn, creditDataSQL, new BeanHandler<>(CreditModel.class));
        return credit;
    }
}