using System.IO;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ModelComponent.Domain;
using ModelComponent.Model;

namespace ModelComponentTests.Model
{
    [TestClass()]
    public class ModelUserTests
    {
        public ModelUser Model;

        [TestInitialize]
        public void SetUp()
        {
            var database = Path.Combine(Directory.GetCurrentDirectory(), "test.db");
            Model = new ModelUser(database);
        }

        [TestCleanup]
        public void CleanUp()
        {
            var database = Path.Combine(Directory.GetCurrentDirectory(), "test.db");
            File.Delete(database);
        }

        [TestMethod]
        public void IsAdding()
        {
            var user = new User(1, "Test", "Password");
            Model.Add(user);
            Assert.IsTrue(Model.GetAll().Contains(user) &&
                          Model.GetAll().Count.Equals(1));
        }

        [TestMethod]
        public void IsDeleting()
        {
            var user = new User(1, "Test", "Password");
            Model.Add(user);
            Model.Delete(user);
            Assert.IsTrue(!Model.GetAll().Contains(user) &&
                          Model.GetAll().Count.Equals(0));
        }

        [TestMethod]
        public void IsUpdating()
        {
            var user = new User(1, "Test", "Password");
            var update = new User(1, "Update", "UpdatePass");
            Model.Add(user);
            Model.Update(user, update);
            Assert.IsTrue(Model.GetAll().Contains(update) &&
                          Model.GetAll().Count.Equals(1));
        }

        [TestMethod]
        public void IsGettingData()
        {
            var user = new User(1, "Test", "Password");
            var other = new User(2, "Other", "Password");
            Model.Add(user);
            Model.Add(other);
            Assert.IsTrue(Model.GetAll().Contains(user) &&
                          Model.GetAll().Contains(other) &&
                          Model.GetAll().Count.Equals(2));
        }

        [TestMethod]
        public void IsFindingItem()
        {
            var user = new User(1, "Test", "Password");
            Model.Add(user);
            Assert.IsTrue(Model.FindElementById(1).Equals(user));
        }
    }
}