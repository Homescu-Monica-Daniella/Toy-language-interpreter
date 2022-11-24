package View;

import Controller.Controller;
import Exceptions.ADTException;
import Exceptions.ExprEvalException;
import Exceptions.StmtExecException;

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
        catch(ADTException | ExprEvalException | StmtExecException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
