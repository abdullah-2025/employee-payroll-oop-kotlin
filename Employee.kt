import java.time.LocalDate

/**
 * Parent class for every employee in the assignment.
 *
 * Tax notes from page 2 of the assignment:
 * - The written percentages are used, as requested.
 * - The values in braces disagree with the written rates for several slabs:
 *   8% vs {0.05}, 15% vs {0.08}, 20% vs {0.15}, 23% vs {0.20},
 *   and 28% vs {0.23}.
 * - Some written ranges exclude exact boundary values such as 45,000.
 *   This implementation makes each upper boundary inclusive.
 * - No rate is supplied above 250,000, so the highest supplied rate (28%)
 *   continues to apply.
 *
 * Tax is a flat percentage of the employee's entire gross earnings; the PDF
 * does not describe a progressive/marginal calculation.
 */
abstract class Employee(
    firstName: String,
    lastName: String,
    nationalIdNumber: String,
    hireDate: LocalDate
) {
    // Kotlin properties provide the get/set methods shown in the class diagram.
    var firstName: String = firstName
        set(value) {
            require(value.isNotBlank()) { "First name cannot be blank." }
            field = value
        }

    var lastName: String = lastName
        set(value) {
            require(value.isNotBlank()) { "Last name cannot be blank." }
            field = value
        }

    var nationalIdNumber: String = nationalIdNumber
        set(value) {
            require(value.isNotBlank()) { "National ID cannot be blank." }
            field = value
        }

    var hireDate: LocalDate = hireDate

    init {
        require(firstName.isNotBlank()) { "First name cannot be blank." }
        require(lastName.isNotBlank()) { "Last name cannot be blank." }
        require(nationalIdNumber.isNotBlank()) { "National ID cannot be blank." }
    }

    abstract fun grossEarnings(): Double

    open fun tax(): Double = taxCalc(grossEarnings())

    open fun netEarnings(): Double = grossEarnings() - tax()

    fun taxCalc(salary: Double): Double {
        require(salary >= 0.0) { "Salary cannot be negative." }

        val taxRate = when {
            salary <= 10_000.0 -> 0.00
            salary <= 20_000.0 -> 0.02
            salary <= 45_000.0 -> 0.08
            salary <= 75_000.0 -> 0.15
            salary <= 100_000.0 -> 0.20
            salary <= 150_000.0 -> 0.23
            else -> 0.28
        }

        return salary * taxRate
    }

    protected fun basicDetails(): String =
        "Name: $firstName $lastName\n" +
            "National ID: $nationalIdNumber\n" +
            "Hire date: $hireDate"

    override fun toString(): String = basicDetails()
}
