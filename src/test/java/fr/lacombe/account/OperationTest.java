package fr.lacombe.account;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

import static fr.lacombe.account.OperationType.DEPOSIT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperationTest {

    @Test
    public void should_compute_the_operation_statement_given_a_balance() {
        final Balance balance = mock(Balance.class);
        final Balance expectedBalance = Balance.of(1L);

        final Amount amount = mock(Amount.class);
        final LocalDateTime timestamp = LocalDateTime.now();
        final Operation operation = Operation.of(DEPOSIT, timestamp, amount);

        when(balance.add(amount)).thenReturn(expectedBalance);
        final OperationStatement statement = operation.computeStatement(balance);

        Assertions.assertThat(statement).isEqualTo(OperationStatement.of(
                DEPOSIT, timestamp, amount, expectedBalance
        ));
    }
}