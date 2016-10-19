# JRestless Examples

[![Build Status](https://travis-ci.org/bbilger/jrestless-examples.svg?branch=master)](https://travis-ci.org/bbilger/jrestless-examples)
[![GitHub issues](https://img.shields.io/github/issues/bbilger/jrestless-examples.svg)](https://github.com/bbilger/jrestless-examples/issues)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/bbilger/jrestless-examples/master/LICENSE)

This repository contains examples for [JRestlesss](https://github.com/bbilger/jrestless).

## Preliminary Note

JRestless does not depend on the [serverless framework](https://github.com/serverless/serverless) but it simplifies the necessary AWS configuration tremendously. So all examples contain a `serverless` configuration and the installation descriptions assume you have `serverless` installed and configured.

You can install `serverless` as described in the docs https://serverless.com/framework/docs/guide/installing-serverless/

To run the AWS examples setup your AWS account as described in the docs https://serverless.com/framework/docs/providers/aws/setup/

## Examples

* [AWS](aws)
  * [API Gateway](aws/gateway)
    * [aws-gateway-showcase](aws/gateway/aws-gateway-showcase)
      * Example showing JRestless' features.
    * [aws-gateway-spring](aws/gateway/aws-gateway-spring)
      * Example showing how to use Spring in JRestless.
    * [aws-gateway-usage-example](aws/gateway/aws-gateway-usage-example)
      * Simple JRestless usage example.
