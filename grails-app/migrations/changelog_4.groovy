databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436220626834-1") {
		addColumn(tableName: "movimiento_de_cuenta") {
			column(name: "concepto", type: "varchar(50)")
		}
	}

	
}
