public class NodeScorer {

    // Tunable constants
    private static final double E_CONST        = Math.E;        // for log() offset
    private static final double NOVELTY_FACTOR = 2.0;           // boost multiplier for completely unvisited
    private static final double ALPHA          = 1.0;           // controls decay of novelty for lightly visited

    /**
     * Computes a combined score for a node.
     *
     * @param rawScore         Base score from your existing ranking logic
     * @param visitCount       Total number of times this node has been visited
     * @param distanceFromRoot Number of edges (or hops) from the root
     * @return adjusted score
     */
    public static double computeScore(double rawScore,
                                      int visitCount,
                                      int distanceFromRoot) {

        // 1) Inverse‐frequency scaling
        //    Dividing by log(visitCount + e) dampens extremely popular nodes
        double freqScaled = rawScore / Math.log(visitCount + E_CONST);

        // 2) Depth penalty
        //    Deep nodes get a smaller weight so they don't sink too low
        double depthWeight = 1.0 / (1 + distanceFromRoot);

        // 3) Novelty boost
        //    Give a solid boost if never visited; else a mild decay factor
        double noveltyBoost;
        if (visitCount == 0) {
            noveltyBoost = NOVELTY_FACTOR;
        } else {
            // you could also do 1 + ALPHA/(visitCount+1) for a gentler boost
            noveltyBoost = 1.0 / Math.log(visitCount + ALPHA);
        }

        // 4) Combine them
        return freqScaled
             * depthWeight
             * noveltyBoost;
    }

    // --- Example usage ---
    public static void main(String[] args) {
        double rawScore   = 50.0;
        int visitCount    = 100;
        int distance      = 3;

        double score = computeScore(rawScore, visitCount, distance);
        System.out.printf("Adjusted score: %.4f%n", score);
    }
}
