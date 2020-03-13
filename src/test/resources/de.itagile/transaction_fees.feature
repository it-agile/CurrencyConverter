Feature: Transaction Fees
  A fee is charged for every transaction

  Scenario: One has to pay no transaction fees from EUR to DKK if fee is 0%
    Given the date is 2019-01-01
    And the transaction fee is 0%
    And the amount is 100 EUR
    When I change it to DKK
    Then The amount I receive is 746.73

#  Scenario: One has to pay 10 EUR transaction fees from EUR to DKK if fee is 10%
#    Given the date is 2019-01-01
#    And the transaction fee is 10%
#    And the amount is 100 EUR
#    When I change it to DKK
#    Then The amount I receive is 672.06