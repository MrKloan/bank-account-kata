package fr.lacombe.account;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static fr.lacombe.account.OperationType.DEPOSIT;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountStatementTest {

    @Test
    public void should_initialize_an_empty_account_statements() {
        final AccountStatement accountStatement = AccountStatement.empty();

        assertThat(accountStatement).isEqualTo(AccountStatement.of(emptyList()));
    }

    @Test
    public void should_get_no_operation_statement_when_the_account_statement_is_empty() {
        final AccountStatement accountStatement = AccountStatement.empty();

        final OperationStatement lastStatement = accountStatement.lastStatement();

        assertThat(lastStatement).isNull();
    }

    @Test
    public void should_update_an_empty_account_statement_with_an_operation() {
        final Operation operation = mock(Operation.class);
        final OperationStatement expectedOperationStatement = mock(OperationStatement.class);
        final AccountStatement accountStatement = AccountStatement.empty();

        when(operation.computeStatement(any())).thenReturn(expectedOperationStatement);
        final AccountStatement updatedAccountStatement = accountStatement.update(operation);

        assertThat(updatedAccountStatement).isEqualTo(AccountStatement.of(singletonList(expectedOperationStatement)));
    }

    @Test
    public void should_update_an_account_statement_with_an_operation() {
        final Operation operation = mock(Operation.class);
        final OperationStatement operationStatement = mock(OperationStatement.class);
        final OperationStatement expectedOperationStatement = mock(OperationStatement.class);
        final AccountStatement accountStatement = AccountStatement.of(singletonList(operationStatement));

        when(operationStatement.calculateNext(operation)).thenReturn(expectedOperationStatement);
        final AccountStatement updatedAccountStatement = accountStatement.update(operation);

        assertThat(updatedAccountStatement).isEqualTo(AccountStatement.of(asList(
                operationStatement,
                expectedOperationStatement
        )));
    }

    @Test
    public void should_get_the_latest_operation_statement() {
        final List<OperationStatement> operationStatements = asList(
                OperationStatement.of(DEPOSIT, LocalDateTime.now(), Amount.of(3L), Balance.of(3L)),
                OperationStatement.of(DEPOSIT, LocalDateTime.now(), Amount.of(5L), Balance.of(8L)),
                OperationStatement.of(DEPOSIT, LocalDateTime.now(), Amount.of(7L), Balance.of(15L))
        );
        final AccountStatement accountStatement = AccountStatement.of(operationStatements);

        final OperationStatement lastStatement = accountStatement.lastStatement();

        assertThat(lastStatement).isEqualTo(operationStatements.get(2));
    }

    @Test
    public void should_create_a_new_account_statement_instance_containing_the_provided_operation_statement() {
        final OperationStatement operationStatement = mock(OperationStatement.class);
        final AccountStatement accountStatement = AccountStatement.empty();

        final AccountStatement result = accountStatement.writeStatement(operationStatement);

        assertThat(accountStatement).isEqualTo(AccountStatement.empty());
        assertThat(result).isEqualTo(AccountStatement.of(singletonList(operationStatement)));
    }
}