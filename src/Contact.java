public class Contact {

    private String name;
    private String phoneNumber;


    public Contact(String aName, String aPhoneNumber) {
            this.name = aName;
            this.phoneNumber = aPhoneNumber;
            }

    public String getName() {
            return this.name;
            }

    public void setName(String name) {
            this.name = name;
            }

    public String getPhoneNumber() {
            return this.phoneNumber;
            }

    public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            }

}
