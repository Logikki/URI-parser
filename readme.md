class visma-identity(URI : string)

path is login, confirm or sign

- Path login:
      source(type:string)
      Example: visma-identity://login?source=severa

- Path confirm:
      source(type:string)
      payment number(type:integer)
      Example: visma-identity://confirm?source=netvisor&paymentnumber=102226

- Path sign:
     source(type: string)
     documentid(type:string)
     Example: visma-identity://sign?source=vismasign&documentid=105ab44