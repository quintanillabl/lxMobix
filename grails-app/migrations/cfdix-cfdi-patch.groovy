databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1497393475140-1") {
		addColumn(tableName: "cfdi") {
			column(name: "version_cfdi", type: "varchar(3)") 
		}
		grailsChange {
            change {
                sql.execute("UPDATE cfdi SET version_cfdi = '3.2'")
            }
            rollback {
            }
		}
		addNotNullConstraint(tableName: "cfdi", columnDataType:'varchar(3)',columnName: "version_cfdi")
	}
}
