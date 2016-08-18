databaseChangeLog = {

	

	changeSet(author: "rcancino (generated)", id: "1471535297070-2") {
		addColumn(tableName: "requisicion") {
			column(name: "forma_de_pago", type: "varchar(255)") 
		}
	}
}
