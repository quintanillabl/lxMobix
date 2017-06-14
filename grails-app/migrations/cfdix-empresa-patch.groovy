databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1497392141576-1") {
		addColumn(tableName: "empresa") {
			column(name: "version_de_cfdi", type: "varchar(3)") 
		}
		grailsChange {
            change {
                sql.execute("UPDATE empresa SET version_de_cfdi = '3.2'")
            }
            rollback {
            }
		}
		addNotNullConstraint(tableName: "empresa", columnDataType:'varchar(3)',columnName: "version_de_cfdi")
	}
}
