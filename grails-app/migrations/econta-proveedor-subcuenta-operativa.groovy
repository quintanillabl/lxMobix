databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1475680397835-1") {
		addColumn(tableName: "proveedor") {
			column(name: "sub_cuenta_operativa", type: "varchar(4)")
		}
	}
}
