# Using ModelMapper to produce PDF from XSLT
<div id="top"></div>

![Maven Build](https://github.com/torkilvatne/xslt-pdf-with-modelmapper/actions/workflows/maven.yml/badge.svg)

![Coverage](.github/badges/jacoco.svg)

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you can construct easy data mapping between objects, convert it into XML and transform it into a PDF.
In this way you can easily automate production of PDFs. The code also shows how to implement QR codes into the PDF.

### Prerequisites

* Maven
  ```sh
  mvn clean install
  ```

### Installation

_Instructions of how to install the project._

1. Clone the repo
   ```sh
   git clone https://github.com/torkilvatne/xslt-pdf-with-modelmapper.git
   ```
2. Install mvn dependencies
   ```sh
   mvn clean install
   ```
3. Run project

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

There are four APIs:

**"/getcustomer/xml/{customerId}/{language}/"** - Fetch the XML of a customer based on ID

**"/getcustomers/xml/"** - Fetch the XML of all customer

**"/getcustomer/pdf/{customerId}/{language}/"** - Fetch the PDF of a customer based on ID

**"/getcustomers/pdf/"** - Fetch the PDF of all customer

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [x] Add Changelog
- [x] Add usage examples with API links

See the [open issues](https://github.com/torkilvatne/xslt-pdf-with-modelmapper/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>

