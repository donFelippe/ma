import 'package:flutter/material.dart';
import '../models/sheet_music.dart';

class AddPage extends StatefulWidget {
  final Function(SheetMusic) onAdd;

  const AddPage({Key? key, required this.onAdd}) : super(key: key);

  @override
  _AddPageState createState() => _AddPageState();
}

class _AddPageState extends State<AddPage> {
  final _formKey = GlobalKey<FormState>();
  final _titleController = TextEditingController();
  final _composerController = TextEditingController();
  final _pdfPathController = TextEditingController();
  final _musicXmlPathController = TextEditingController();
  String _difficulty = 'Beginner'; // Default difficulty

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Add Sheet Music'),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                controller: _titleController,
                decoration: const InputDecoration(labelText: 'Title'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a title';
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _composerController,
                decoration: const InputDecoration(labelText: 'Composer'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a composer';
                  }
                  return null;
                },
              ),
              DropdownButtonFormField<String>(
                value: _difficulty,
                onChanged: (String? newValue) {
                  setState(() {
                    _difficulty = newValue!;
                  });
                },
                items: <String>['Beginner', 'Intermediate', 'Advanced']
                    .map<DropdownMenuItem<String>>((String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(value),
                  );
                }).toList(),
                decoration: const InputDecoration(labelText: 'Difficulty'),
              ),
              TextFormField(
                controller: _pdfPathController,
                decoration: const InputDecoration(labelText: 'PDF Path'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a PDF path';
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _musicXmlPathController,
                decoration: const InputDecoration(labelText: 'MusicXML Path'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a MusicXML path';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  if (_formKey.currentState!.validate()) {
                    final newSheetMusic = SheetMusic(
                      title: _titleController.text,
                      composer: _composerController.text,
                      difficulty: _difficulty,
                      pdfPath: _pdfPathController.text,
                      musicXmlPath: _musicXmlPathController.text,
                    );
                    widget.onAdd(newSheetMusic);
                    Navigator.pop(context);
                  }
                },
                child: const Text('Add Sheet Music'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}