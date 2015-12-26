import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;

/**
 * CSP solver for "Aristotle's Number Puzzle"
 * Created by tomdooner on 12/25/15.
 */
public class AristotleSolver {
    public static void main(String [] args) {
        // 1. Create a Solver
        Solver solver = new Solver("aristotle's number puzzle");

        // 2. Create variables through the variable factory
        IntVar sum = VariableFactory.fixed(38, solver);
        IntVar[] vars = VariableFactory.boundedArray("A", 19, 1, 19, solver);

        /*
        The puzzle looks like this:
                 A  B  C
               D  E  F  G
              H  I  J  K  L
               M  N  O  P
                 Q  R  S
         */

        IntVar a = vars[0];
        IntVar b = vars[1];
        IntVar c = vars[2];
        IntVar d = vars[3];
        IntVar e = vars[4];
        IntVar f = vars[5];
        IntVar g = vars[6];
        IntVar h = vars[7];
        IntVar i = vars[8];
        IntVar j = vars[9];
        IntVar k = vars[10];
        IntVar l = vars[11];
        IntVar m = vars[12];
        IntVar n = vars[13];
        IntVar o = vars[14];
        IntVar p = vars[15];
        IntVar q = vars[16];
        IntVar r = vars[17];
        IntVar s = vars[18];


        // 3. Create and post constraints by using constraint factories
        solver.post(IntConstraintFactory.alldifferent(vars));

        // left -> right
        solver.post(IntConstraintFactory.sum(new IntVar[]{a, b, c}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{d, e, f, g}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{h, i, j, k, l}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{m, n, o, p}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{q, r, s}, sum));

        // top -> bottom left
        solver.post(IntConstraintFactory.sum(new IntVar[]{a, d, h}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{b, e, i, m}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{c, f, j, n, q}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{g, k, o, r}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{l, p, s}, sum));

        // top -> bottom right
        solver.post(IntConstraintFactory.sum(new IntVar[]{h, m, q}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{d, i, n, r}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{a, e, j, o, s}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{b, f, k, p}, sum));
        solver.post(IntConstraintFactory.sum(new IntVar[]{c, g, l}, sum));

        // 4. Define the search strategy
        solver.set(IntStrategyFactory.lexico_LB(vars));

        // 5. Launch the resolution process
        solver.findSolution();

        Chatterbox.printStatistics(solver);

        // 6. Print the answer
        System.out.println("From top left to bottom right:");
        for (IntVar ii : vars) {
            System.out.printf("%d\n", ii.getValue());
        }
    }
}
