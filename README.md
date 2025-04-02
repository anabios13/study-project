![image](https://github.com/user-attachments/assets/6c2bb1da-7366-4d68-9d41-5a24fa8f8e5a)
![image](https://github.com/user-attachments/assets/cb79b1a0-768d-40ea-b887-7c8446506db0)


Full REST API for insurance company. Authorization using JWT token.

Case\Assignment – main work entity unique for each insurance case that describes the vehicle, contact information, estimates and any supplements along with final results.

Estimate – part of an assignment document with estimated cost of repair.

Estimator – repair facility representative that is responsible for reviewing the assignment and providing
estimated costs of repair.

Total loss – insurance case in which car damage considered unrepairable or repair costs exceed the price of a car.

VIN – vehicle identification number, also known as a VIN, which is a 17-character number that encodes
specific information about the particular vehicle

Actors 

Client – the owner of an insured vehicle.
Insurance Company – company that handles the specific assignment. Gives final decision on each
assignment. Provides money reimbursement for car repairs or part of car value in total loss case.

Repair facility – affiliated repair center that provides estimates for assigned cases and does the actual car
repairs.

Client:

● Login in to the system

● View client specific incidents history and their current status

● By specific insurance (for now let’s say that client already has insurance associated with him)

● Create new unassigned case

o Provide vehicle VIN number and vehicle information

o Provide text description of damages

o Provide photos for new case

o Provide contact information


Insurance Company:

● Login into the system

● Receive an assignment

● Provides additional details

● Provides additional advices\notes to the estimator

● Sends assignment to a specific repair facility

● Review estimate

o Send estimate for rereview in case of any issues

● Provide final decision – repair or total loss reimbursement

● Review any additional supplements for a case and keep\change decision

Repair facility

● Login into the system

● Receive an assignment

● Configure taxes and work rates

● Provide assignment estimation

o List all parts that should be replaced based on the photos provided

o Provide additional charges based on work rates, taxes and transportation costs (if any)

● Finish estimation and send assignment back to Insurance company

