using System.Collections.Generic;

namespace WindowsFormsApplication2
{
    public class DatabaseTable
    {
        public string Name;
        public string PrimaryKey;
        public string ForeignKey;
        public string Parent;
        public List<string> Columns;
        public List<string> Parameters;
    }
}
