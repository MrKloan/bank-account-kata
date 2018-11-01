package fr.lacombe.account;

import org.junit.Test;

import static fr.lacombe.account.OperationType.DEPOSIT;
import static fr.lacombe.account.OperationType.WITHDRAWAL;
import static org.mockito.Mockito.*;

public class OperationTypeTest {

    @Test
    public void should_execute_a_deposit_operation() {
        final Balance balance = mock(Balance.class);
        final Amount amount = mock(Amount.class);

        DEPOSIT.execute(balance, amount);

        verify(balance).add(amount);
        verify(balance, times(0)).subtract(amount);
    }

    @Test
    public void should_execute_a_withdrawal_operation() {
        final Balance balance = mock(Balance.class);
        final Amount amount = mock(Amount.class);

        WITHDRAWAL.execute(balance, amount);

        verify(balance).subtract(amount);
        verify(balance, times(0)).add(amount);
    }
}