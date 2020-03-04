abstract class CreateScripts {
  static const String TRANSACTION = ""
      "CREATE TABLE transactions ("
      "    id       INTEGER PRIMARY KEY NOT NULL,"
      "    value    REAL NOT NULL,"
      "    category TEXT NOT NULL,"
      "    date     TEXT NOT NULL,"
      "    type     TEXT CHECK(type IN('REVENUE', 'EXPENSE')"
      ");";
  static const String BIG_BANG = TRANSACTION;
}
