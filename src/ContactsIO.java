public class ContactsIO {

        private String name;
        private String phoneNumber;


        public ContactsIO(String aName, String aPhoneNumber) {
            this.name = aName;
            this.phoneNumber = aPhoneNumber;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getContactInto() {
            String contactInfo = getName() + " " + getPhoneNumber();
            return contactInfo;
        }

}
