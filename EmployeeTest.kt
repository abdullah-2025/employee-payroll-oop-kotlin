import java.time.LocalDate
import java.util.Locale

fun main() {
    Locale.setDefault(Locale.US)

    val employees: List<Employee> = listOf(
        HourlyEmployee(
            "Ali", "Khan", "42101-1111111-1",
            LocalDate.of(2025, 1, 10), 250.0, 50.0
        ),
        SalariedEmployee(
            "Sara", "Ahmed", "42101-2222222-2",
            LocalDate.of(2024, 6, 15), 45_000.0
        ),
        CommissionEmployee(
            "Hamza", "Malik", "42101-3333333-3",
            LocalDate.of(2023, 3, 20), 500_000.0, 0.15
        ),
        BasePlusCommissionEmployee(
            "Ayesha", "Raza", "42101-4444444-4",
            LocalDate.of(2022, 9, 1), 800_000.0, 0.20, 50_000.0
        )
    )

    println("EMPLOYEE EARNINGS AND TAX REPORT")
    println("================================")
    println(
        "Tax policy: written percentages are used. The conflicting decimal " +
            "values in braces are ignored. Upper slab boundaries are inclusive."
    )

    // Calling overridden methods through Employee references demonstrates polymorphism.
    for (employee in employees) {
        println("\n--------------------------------")
        println(employee)
        println("Gross earnings: %,.2f".format(employee.grossEarnings()))
        println("Tax amount:    %,.2f".format(employee.tax()))
        println("Net earnings:  %,.2f".format(employee.netEarnings()))
    }

    printTaxBoundaryTests(employees.first())
}

private fun printTaxBoundaryTests(employee: Employee) {
    val boundarySalaries = listOf(
        10_000.0,
        20_000.0,
        45_000.0,
        75_000.0,
        100_000.0,
        150_000.0,
        200_000.0,
        250_000.0,
        250_000.01
    )

    println("\n\nTAX SLAB BOUNDARY TESTS")
    println("=======================")
    println("Salary          Tax")

    for (salary in boundarySalaries) {
        println("%,10.2f  ->  %,10.2f".format(salary, employee.taxCalc(salary)))
    }
}
