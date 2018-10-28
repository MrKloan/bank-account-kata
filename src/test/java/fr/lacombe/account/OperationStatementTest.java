package fr.lacombe.account;

import org.junit.Test;

import static fr.lacombe.account.Operation.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;

public class OperationStatementTest {

    @Test
    public void should_calculate_next_operation() {
        final OperationStatement statement = OperationStatement.of(DEPOSIT, Amount.of(3L), Balance.of(3L));

        final OperationStatement result = statement.calculateNext(DEPOSIT, Amount.of(5L));

        assertThat(result).isEqualTo(OperationStatement.of(DEPOSIT, Amount.of(5L), Balance.of(8L)));
    }
}