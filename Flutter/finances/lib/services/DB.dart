import 'package:finances/model/DataModel.dart';
import 'package:finances/services/CreateScripts.dart';
import 'package:sqflite/sqflite.dart';

abstract class DB {
  static Database _db;
  static int get _version => 1;

  static Future<void> init() async {
    if (_db != null) {
      return;
    }
    try {
      String _path = await getDatabasesPath() + "finances";
      _db = await openDatabase(_path, version: _version, onCreate: onCreate);
    } catch (ex) {
      print(ex);
    }
  }

  static void onCreate(Database db, int version) async => await db.execute(CreateScripts.BIG_BANG);
  static Future<List<Map<String, dynamic>>> query(String table) async => _db.query(table);
  static Future<int> insert(String table, DataModel model) async => await _db.insert(table, model.toMap());
  static Future<int> update(String table, DataModel model) async => await _db.update(table, model.toMap(), where: "id = ?", whereArgs: [model.id]);
  static Future<int> delete(String table, DataModel model) async => await _db.delete(table, where: "id = ?", whereArgs: [model.id]);
}
