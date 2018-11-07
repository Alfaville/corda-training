package net.corda.training.state

import net.corda.core.contracts.Amount
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.Party
import net.corda.finance.DOLLARS
import java.util.*

/**
 * This is where you'll add the definition of your state object. Look at the unit tests in [IOUStateTests] for
 * instructions on how to complete the [IOUState] class.
 *
 */
data class IOUState(
        val amount: Amount<Currency>,
        val lender: Party,
        val borrower: Party,
        val paid: Amount<Currency> = 0.DOLLARS,
        override val linearId: UniqueIdentifier = UniqueIdentifier()): LinearState {

    override val participants: List<Party> get() = listOf(lender, borrower)

    fun pay(value: Amount<Currency>): IOUState {
        return IOUState(amount, lender, borrower, paid).copy(paid = paid.plus(value))
    }

    fun withNewLender(newLender: Party): IOUState {
        return IOUState(amount, lender, borrower).copy(lender = newLender)
    }

//    fun pay(amountToPay : Amount<Currency>) = copy(paid = paid.plus(amountToPay))

//    fun withNewLender(newLender : Party) = copy(lender = newLender)

}