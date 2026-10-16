import java.time.LocalDate

open class CommissionEmployee(
    firstName: String,
    lastName: String,
    nationalIdNumber: String,
    hireDate: LocalDate,
    val grossSales: Double,
    val commissionRate: Double
) : Employee(firstName, lastName, nationalIdNumber, hireDate) {
    init {
        require(grossSales >= 0.0) { "Gross sales cannot be negative." }
        require(commissionRate in 0.0..1.0) {
            "Commission rate must be between 0.0 and 1.0."
        }
    }

    override fun grossEarnings(): Double = grossSales * commissionRate

    override fun tax(): Double = taxCalc(grossEarnings())

    override fun netEarnings(): Double = grossEarnings() - tax()

    override fun toString(): String =
        "Employee type: Commission employee\n" +
            "${basicDetails()}\n" +
            "Gross sales: %.2f\nCommission rate: %.2f%%".format(
                grossSales,
                commissionRate * 100.0
            )
}
