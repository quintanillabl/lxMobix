databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436927869272-1") {
		dropColumn(columnName: "pagos_aplicados", tableName: "cuenta_por_pagar")
	}

	changeSet(author: "rcancino (generated)", id: "1436927869272-2") {
		dropColumn(columnName: "requisitado", tableName: "cuenta_por_pagar")
	}
}
