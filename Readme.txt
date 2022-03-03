Design Decisions:
-----------------
For the database part, I create only a Document model since my design it is
based on noSQL schema like mongoDb but i used h2 for simplicity for this exercise.
If I have used relation database I would create separate schema for journal and book.

Another approach that we can implement is instead of having a future execute create watermark method,
to have a schedule task that will fetch all the pending request from processing table and execute the
create watermark method.
