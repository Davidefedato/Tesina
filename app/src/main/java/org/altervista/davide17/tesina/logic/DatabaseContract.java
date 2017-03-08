package org.altervista.davide17.tesina.logic;

    import android.provider.BaseColumns;

public final class
        DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor. Consigliano il costruttore vuoto
    public DatabaseContract() {
    }

    /* Definisce il contentuto della tabella livello*/
    public static abstract class LevelEntry implements BaseColumns {
        public static final String TABLE_NAME = "Moto";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_MARCA = "Marca";
        public static final String COLUMN_NAME_MODELLO = "Modello";
        public static final String COLUMN_NAME_CILINDRATA = "Cilindrata";
        public static final String COLUMN_NAME_CATEGORIA = "Categoria";

        // SQL per creare la tabella
        static final String SQL_CREATE =
                "CREATE TABLE `Moto` (\n" +
                        "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "\t`Marca`\tTEXT,\n" +
                        "\t`Modello`\tTEXT,\n" +
                        "\t`Cilindrata`\tTEXT,\n" +
                        "\t`Categoria`\tTEXT\n" +
                        ");";

        // SQL per cancellare la tabella
        static final String SQL_DROPTABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }

}

