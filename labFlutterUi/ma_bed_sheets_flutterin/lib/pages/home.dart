import 'package:flutter/material.dart';
import 'add.dart';
class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('BedSheets'),
        centerTitle: true,
        backgroundColor: Colors.white,
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(4.0),
          child: Container(
            color: Colors.black,
            height: 4.0,
          ),
        ),
        actions: [
          IconButton(
            icon: const CircleAvatar(
              backgroundColor: Colors.blue,
              child: Icon(Icons.account_circle, color: Colors.white),
            ),
            onPressed: () {
              // Handle account button press
            },
          ),
        ],
      ),
      body: const Center(
        child: Text('Welcome to BedSheets!'),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Navigate to the Add page
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const AddPage()),
          );
        },
        child: const Icon(Icons.add),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }
}