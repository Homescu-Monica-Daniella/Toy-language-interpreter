package View;

import Controller.Controller;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;

import java.io.IOException;
import java.util.Scanner;

public class RunExample extends Command {

    private Controller ctr;

    public RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            System.out.println("display flag: ");
            Scanner readFlag = new Scanner(System.in);
            boolean flg = readFlag.nextBoolean();
            ctr.setFlg(flg);
            ctr.allStep();
        }
        catch(ADTException | ExpressionEvaluationException | StatementExecutionException | IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}