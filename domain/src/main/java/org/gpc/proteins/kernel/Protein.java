package org.gpc.proteins.kernel;

import java.util.UUID;

public record Protein (String fastaName, String source, String organism, String clasification, String clasificationEC,
                       String authors, String fastaSequence) {


}
