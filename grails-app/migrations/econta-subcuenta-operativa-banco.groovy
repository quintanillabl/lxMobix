databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1476828066655-1") {
		addColumn(tableName: "cuenta_bancaria") {
			column(name: "sub_cuenta_operativa", type: "varchar(4)")
		}
	}
}
