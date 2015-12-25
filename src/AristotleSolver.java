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
        Solver solver = new Solver("my first problem");

        // 2. Create variables through the variable factory
        IntVar x = VariableFactory.bounded("X", 0, 5, solver);
        IntVar y = VariableFactory.bounded("Y", 0, 5, solver);

        // 3. Create and post constraints by using constraint factories
        solver.post(IntConstraintFactory.arithm(x, "+", y, "<", 5));

        // 4. Define the search strategy
        solver.set(IntStrategyFactory.lexico_LB(x, y));

        // 5. Launch the resolution process
        if (solver.findSolution()) {
            do {
                System.out.printf("%d + %d < 5\n", x.getValue(), y.getValue());
            } while (solver.nextSolution());
        }

        // 6. Print search statistics
        // Chatterbox.printStatistics(solver);
    }
}
