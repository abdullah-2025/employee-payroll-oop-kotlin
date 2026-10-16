import java.time.LocalDate

class HourlyEmployee(
    firstName: String,
    lastName: String,
    nationalIdNumber: String,
    hireDate: LocalDate,
    val wage: Double,
    val hours: Double
) : Employee(firstName, lastName, nationalIdNumber, hireDate) {
    init {
        require(wage >= 0.0) { "Hourly wage cannot be negative." }
        require(hours >= 0.0) { "Hours worked cannot be negative." }
    }

    // Hours above 40 are paid at one-and-a-half times the normal wage.
    override fun grossEarnings(): Double =
        if (hours <= 40.0) {
            wage * hours
        } else {
            (40.0 * wage) + ((hours - 40.0) * wage * 1.5)
        }

    override fun tax(): Double = taxCalc(grossEarnings())

    override fun netEarnings(): Double = grossEarnings() - tax()

    override fun toString(): String =
        "Employee type: Hourly employee\n" +
            "${basicDetails()}\n" +
            "Hourly wage: %.2f\nHours worked: %.2f".format(wage, hours)
}
