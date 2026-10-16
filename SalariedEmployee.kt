import java.time.LocalDate

class SalariedEmployee(
    firstName: String,
    lastName: String,
    nationalIdNumber: String,
    hireDate: LocalDate,
    monthlySalary: Double
) : Employee(firstName, lastName, nationalIdNumber, hireDate) {
    var monthlySalary: Double = monthlySalary
        set(value) {
            require(value >= 0.0) { "Monthly salary cannot be negative." }
            field = value
        }

    init {
        require(monthlySalary >= 0.0) { "Monthly salary cannot be negative." }
    }

    override fun grossEarnings(): Double = monthlySalary

    override fun tax(): Double = taxCalc(grossEarnings())

    override fun netEarnings(): Double = grossEarnings() - tax()

    override fun toString(): String =
        "Employee type: Salaried employee\n" +
            "${basicDetails()}\n" +
            "Monthly salary: %.2f".format(monthlySalary)
}
