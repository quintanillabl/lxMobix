databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1500309585536-5") {
		addColumn(tableName: "venta") {
			column(name: "uso_cfdi", type: "varchar(3)")
		}
	}
}

