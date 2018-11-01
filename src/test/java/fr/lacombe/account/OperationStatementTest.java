package fr.lacombe.account;

import org.junit.Test;

import java.time.LocalDateTime;

import static fr.lacombe.account.OperationType.DEPOSIT;
import static org.mockito.Mockito.*;

public class OperationStatementTest {

    @Test
    public void should_calculate_next_operation() {
        final Operation operation = mock(Operation.class);
        final Balance balance = mock(Balance.class);
        final OperationStatement statement = OperationStatement.of(DEPOSIT, LocalDateTime.now(), mock(Amount.class), balance);

        statement.calculateNext(operation);

        verify(operation).computeStatement(balance);
    }
}