import org.junit.*
import static groovy.test.GroovyAssert.*
import com.claimvantage.sjsl.Org

class CreateScratchOrgTest extends BaseTest {
    def createScratchOrg

    @Before
    void setUp() {
        super.setUp()

        createScratchOrg = loadScript("vars/createScratchOrg.groovy")
        
        //Mock shell returning all values for simplicity
        helper.registerAllowedMethod("shWithResult", [ String ]) { 
           opts -> ["username": "test-drgwjqh3xsn0@example.com", "orgId": "00DS0000003O1sNMAS", "password": "g5nPgf4sdf2", "instanceUrl": "https://ability-saas-8856-dev-ed.lightning.force.com"]
        }

        binding.setVariable("env", [BRANCH_NAME: 'master'])
    }
    
    @Test
    void callStep() {
       Org org = new Org("testScratchOrg.json")
       createScratchOrg(org)
       printCallStack()
    }
}
