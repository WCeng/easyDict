# easyDict
This is a dictionary for Android by ContentProvider to query 

# Usage
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.simpleName
    }

    private val AUTHORITY = "com.wceng.easydict.provider"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.queryIdiom.setOnClickListener {
            val uri = Uri.parse("content://$AUTHORITY/idiom/宾人")
            val cursor = contentResolver.query(uri, null, null, null, null)
            cursor?.let {
                while (it.moveToNext()) {
                    val value1 = it.getString(0)
                    val value2 = it.getString(1)
                    val value3 = it.getString(2)
                    val value4 = it.getString(3)
                    val value5 = it.getString(4)
                    Log.d(TAG, "onCreate: $value1")
                    Log.d(TAG, "onCreate: $value2")
                    Log.d(TAG, "onCreate: $value3")
                    Log.d(TAG, "onCreate: $value4")
                    Log.d(TAG, "onCreate: $value5")
                }
                cursor.close()
            }
        }

        binding.queryWord.setOnClickListener {
            val uri = Uri.parse("content://$AUTHORITY/word/你")
            val cursor = contentResolver.query(uri, null, null, null, null)
            cursor?.let {
                while (it.moveToNext()) {
                    val value1 = it.getString(0)
                    val value2 = it.getString(1)
                    val value3 = it.getString(2)
                    val value4 = it.getString(3)
                    val value5 = it.getString(4)
                    val value6 = it.getString(5)
                    val value7 = it.getString(6)
                    val value8 = it.getString(7)
                    val value9 = it.getString(8)
                    Log.d(TAG, "onCreate: $value1")
                    Log.d(TAG, "onCreate: $value2")
                    Log.d(TAG, "onCreate: $value3")
                    Log.d(TAG, "onCreate: $value4")
                    Log.d(TAG, "onCreate: $value5")
                    Log.d(TAG, "onCreate: $value6")
                    Log.d(TAG, "onCreate: $value7")
                    Log.d(TAG, "onCreate: $value8")
                    Log.d(TAG, "onCreate: $value9")
                }
                cursor.close()
            }
        }

    }
}
