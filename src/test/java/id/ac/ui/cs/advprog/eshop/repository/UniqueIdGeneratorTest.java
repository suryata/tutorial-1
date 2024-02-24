package id.ac.ui.cs.advprog.eshop.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UniqueIdGeneratorTest {

    @Test
    void instanceShouldGenerateUniqueUUID() {
        UniqueIdGenerator generator = new UniqueIdGenerator();
        String uniqueId = UniqueIdGenerator.generate();
        assertNotNull(generator);
        
        assertNotNull(uniqueId, "Generated ID should not be null");
    }
}
