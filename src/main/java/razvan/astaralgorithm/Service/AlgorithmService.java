package razvan.astaralgorithm.Service;

import razvan.astaralgorithm.Domain.Algorithm;

public class AlgorithmService {
    private Algorithm algorithm;

    public AlgorithmService(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
