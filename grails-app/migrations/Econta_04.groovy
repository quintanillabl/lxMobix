databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445453668855-1") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "concepto", type: "varchar(50)")
		}
	}
}
