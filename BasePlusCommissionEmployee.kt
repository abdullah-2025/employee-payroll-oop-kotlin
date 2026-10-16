import java.time.LocalDate

class BasePlusCommissionEmployee(
    firstName: String,
    lastName: String,
    nationalIdNumber: String,
    hireDate: LocalDate,
    grossSales: Double,
    commissionRate: Double,
    val baseMonthlySalary: Double
) : CommissionEmployee(
    firstName,
    lastName,
    nationalIdNumber,
    hireDate,
    grossSales,
    commissionRate
) {
    init {
        require(baseMonthlySalary >= 0.0) { "Base monthly salary cannot be negative." }
    }

    override fun grossEarnings(): Double =
        baseMonthlySalary + super.grossEarnings()

    override fun tax(): Double = taxCalc(grossEarnings())

    override fun netEarnings(): Double = grossEarnings() - tax()

    override fun toString(): String =
        "Employee type: Base-plus-commission employee\n" +
            "${basicDetails()}\n" +
            "Gross sales: %.2f\nCommission rate: %.2f%%\nBase monthly salary: %.2f".format(
                grossSales,
                commissionRate * 100.0,
                baseMonthlySalary
            )
}
