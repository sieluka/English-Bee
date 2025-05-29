package com.example.englishbee.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase      //  ← WAŻNE
import androidx.sqlite.db.SupportSQLiteDatabase   //  ← WAŻNE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [GrammarQuestion::class, IrregularVerb::class,VocabularyWord::class], version =2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun grammarDao(): GrammarDao
    abstract fun verbDao(): IrregularVerbDao
    abstract fun vocabularyDao(): VocabularyDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context, scope: CoroutineScope): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "englishbee.db"
                )
                    /*  ─── POPRAWIONA nazwa klasy ─── */
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {

                            super.onCreate(db)
                            scope.launch {
                                get(context, scope).vocabularyDao().insertAll(wordsData())
                                get(context, scope).verbDao().insertAll(verbData())
                                get(context, scope)
                                    .grammarDao()
                                    .insertAll(sampleData())
                            }
                        }
                    })
                    .build().also { INSTANCE = it }
            }

        private fun sampleData() = listOf(
            GrammarQuestion(sentence = "I ____ to school yesterday.",      answer = "went"),
            GrammarQuestion(sentence = "She has ____ her homework.",       answer = "done"),
            GrammarQuestion(sentence = "They ____ playing football now.",  answer = "are"),
            GrammarQuestion(sentence = "We ____ live in London.",          answer = "don’t"),
            GrammarQuestion(sentence = "He ____ coffee every morning.",    answer = "drinks"),

            // poziom B1
            GrammarQuestion(sentence = "If I ____ time, I would travel more.", answer = "had"),
            GrammarQuestion(sentence = "The film was ____ than I expected.",   answer = "better"),
            GrammarQuestion(sentence = "She said she ____ the letter.",        answer = "had sent"),

            // poziom B2–C1
            GrammarQuestion(sentence = "Hardly ____ they arrived when it started to rain.", answer = "had"),
            GrammarQuestion(sentence = "I’d rather you ____ tomorrow.",                     answer = "came"),
            GrammarQuestion(sentence = "No sooner ____ the call than he left.",             answer = "had received")

        )
        private fun verbData() = listOf(
            IrregularVerb(base = "go",    past = "went",  participle = "gone"),
            IrregularVerb(base = "eat",   past = "ate",   participle = "eaten"),
            IrregularVerb(base = "see",   past = "saw",   participle = "seen"),
            IrregularVerb(base = "write", past = "wrote", participle = "written"),
            IrregularVerb(base = "begin", past = "began", participle = "begun"),
            IrregularVerb(base = "speak", past = "spoke", participle = "spoken"),
            IrregularVerb(base = "drink", past = "drank", participle = "drunk")
        )
        private fun wordsData() = listOf(
            VocabularyWord(english = "apple", polish = "jabłko"),
            VocabularyWord(english = "book", polish = "książka"),
            VocabularyWord(english = "dog", polish = "pies"),
            VocabularyWord(english = "house", polish = "dom"),
            VocabularyWord(english = "car", polish = "samochód")
        )
    }
}
