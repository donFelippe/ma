import 'package:flutter/material.dart';
import '../models/sheet_music.dart';
import '../widgets/sheet_music_card.dart';
import 'add.dart';
import 'undo.dart';
class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final List<SheetMusic> _sheetMusicList = [
    SheetMusic(
      title: "Fur Elise",
      composer: "Ludwig van Beethoven",
      difficulty: "Intermediate",
      pdfPath: "Bach_Minuet_in_G_Major_BWV_Anh._114.mxl",
      musicXmlPath: null,
    ),
  ];

  void _addSheetMusic(SheetMusic sheetMusic) {
    setState(() {
      _sheetMusicList.add(sheetMusic);
    });
  }

  void _deleteSheetMusic(int index) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Confirm Deletion'),
        content: const Text('Are you sure you want to delete this sheet music?'),
        actions: [
          TextButton(
            onPressed: () {
              Navigator.pop(context);
            },
            child: const Text('Cancel'),
          ),
          TextButton(
            onPressed: () {
              setState(() {
                _sheetMusicList.removeAt(index);
              });
              Navigator.pop(context);
            },
            child: const Text('Delete'),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('BedSheets'),
        centerTitle: true,
      ),
      body: GridView.builder(
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          crossAxisSpacing: 8.0,
          mainAxisSpacing: 8.0,
          childAspectRatio: 1,
        ),
        itemCount: _sheetMusicList.length,
        itemBuilder: (context, index) {
          final sheetMusic = _sheetMusicList[index];
          return SheetMusicCard(
            sheetMusic: sheetMusic,
            onDelete: () => _deleteSheetMusic(index),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => UpdatePage(
                    sheetMusic: sheetMusic,
                    onUpdate: (updatedSheetMusic) {
                      setState(() {
                        _sheetMusicList[index] = updatedSheetMusic;
                      });
                    },
                  ),
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => AddPage(
                onAdd: _addSheetMusic,
              ),
            ),
          );
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
